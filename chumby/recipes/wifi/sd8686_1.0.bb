inherit module
DESCRIPTION = "Driver for sd8686 wifi chipset found in Silvermoon"
HOMEPAGE = "http://www.chumby.com"
SECTION = "kernel/modules"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/wifi-58300.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/sd8686/wlan_src/"

EXTRA_OEMAKE = "KERNDIR=${STAGING_KERNEL_DIR}"

do_install() {
        install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
        install -m 0644 sd8xxx${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra/
        install -d ${D}/lib/firmware/mrvl
	install -m 0644 ../FwImage/sd8686.bin ${D}/lib/firmware/mrvl
	install -m 0644 ../FwImage/helper_sd.bin ${D}/lib/firmware/mrvl
}

FILES_${PN}-firmware = "/lib/firmware"
PACKAGES =+ "${PN}-firmware"
RDEPENDS_${PN} = "${PN}-firmware"

SRC_URI[md5sum] = "4b70c7cf32f44f66c25180c4b00fc5a2"
SRC_URI[sha256sum] = "d5070581755b7f99bb75947a5db3540fa42f25e291a9df85cc30c24095a8b2b0"
