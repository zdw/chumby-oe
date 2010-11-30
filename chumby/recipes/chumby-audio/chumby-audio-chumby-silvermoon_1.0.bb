
DESCRIPTION = "Chumby basic audio setup scripts for Silvermoon"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/partitions-2.0-58301.tar.gz;name=parts \
           http://files.chumby.com/source/openembedded/partitions-silvermoon-59026.tar.gz;name=silvermoon \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/rfs1/usr/chumby/scripts"

COMPATIBLE_MACHINE = "chumby-silvermoon"


do_install () {
#
# Create directories and install device independent scripts
#
    install -d ${D}/usr/chumby/scripts

    install -m 0755    ${WORKDIR}/src/rfs1/usr/chumby/scripts/enable_audio.sh ${D}/usr/chumby/scripts/enable_audio.sh

#    ln -sf /psp/asound.conf ${D}${sysconfdir}/asound.conf
}

FILES_${PN} = "/usr/chumby/scripts"

SRC_URI[parts.md5sum] = "9a217adbe77c43c97e7e4c959ec16439"
SRC_URI[parts.sha256sum] = "db94b9d3b4bd87b8712c407575019d29f7a0563c241261a29454de2cc1bece7a"
SRC_URI[silvermoon.md5sum] = "180e52fefb842d9ad4c47f585aaabe0d"
SRC_URI[silvermoon.sha256sum] = "d3e85e70f9f5ad06633915e910ef11bc4d5c4cd0a9c73dcb6c58c1187fe320dd"
