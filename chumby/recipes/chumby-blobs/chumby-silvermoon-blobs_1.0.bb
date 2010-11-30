DESCRIPTION = "Chumby boot blobs - miscellaneous binary blobs"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"
PROVIDES = "chumby-blobs"
COMPATIBLE_MACHINE = "chumby-silvermoon"

SRC_URI = "http://files.chumby.com/source/openembedded/chumby-silvermoon-blobs-59209.tar.gz"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/config"

do_compile() {
    true
}


# This generates a chumby_stub.bin bootstream blob

# chumby_stub.bin is a ROM, with no gnu_hash.
INSANE_SKIP_${PN} = True

FILES_${PN} = "/boot"

do_install() {
    install -d ${D}/boot
    install -m 0755 ${S}/obm.bin ${D}/boot/obm.bin
}

do_runstrip() {
    true
}

# Copy the resulting file to the image directory
do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    install ${S}/obm.bin ${DEPLOY_DIR_IMAGE}/obm.bin
    package_stagefile_shell ${DEPLOY_DIR_IMAGE}/obm.bin
}
do_deploy[dirs] = "${S}"
addtask deploy before do_package_stage after do_compile

SRC_URI[md5sum] = "b90d918ee1d200b32e50c2e0f047fd14"
SRC_URI[sha256sum] = "2143d2b6b6a2d881492f9293252475d0f30af01fd09f2a1da42e0dcc860e0368"
