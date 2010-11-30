#@TYPE: Machine
#@Name: Silvermoon Armada 100 Development Platform
#@DESCRIPTION: Machine configuration for chumby Silvermoon board


IMAGE_FSTYPES = "ext3"
IMAGE_CMD_ext3 = "genext2fs -i 20000 -b ${ROOTFS_SIZE} -d ${IMAGE_ROOTFS} ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext3 ${EXTRA_IMAGECMD}; tune2fs -j ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext3"
EXTRA_IMAGEDEPENDS += "makedisk-native config-util-native virtual/kernel virtual/bootloader chumby-blobs"
MACHINE_POSTPROCESS_COMMAND = "chumby_make_rom"
CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"

chumby_make_rom () {

    # Make config block
    config_util --cmd=create \
        --mbr=/dev/zero \
        --configname=${CNPLATFORM} \
        --build_ver=1000 --force --pad \
        --blockdef=${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin,1507328,u-bt,1,0,0,0 \
        --blockdef=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin,3932160,krnA,1,0,0,0 \
        --blockdef=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin,3932160,krnB,1,0,0,0 \
        > ${DEPLOY_DIR_IMAGE}/config_block.bin


    # Make bootsector
    rm -f ${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin
    touch ${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=0 count=96 if=${DEPLOY_DIR_IMAGE}/obm.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=96 count=32 if=${DEPLOY_DIR_IMAGE}/config_block.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=128 count=2944 if=${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=3072 count=7680 if=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=10752 count=7680 if=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin


    # Make full boot ROM
    install -d ${DEPLOY_DIR_IMAGE}
    makedisk -o ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img \
             -a 16M:0x53:${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin \
             -a ${ROOTFS_SIZE}k:0x83:${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.ext3 \
             -a ${ROOTFS_SIZE}k:0x83:${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.ext3
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img
}

