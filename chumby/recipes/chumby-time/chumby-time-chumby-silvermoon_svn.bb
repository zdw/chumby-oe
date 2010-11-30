DESCRIPTION = "Chumby basic time setup scripts for Silvermoon"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPL"
PR = "r0"

RDEPENDS = "cpi-${MACHINE} ntp"

SRC_URI = "http://files.chumby.com/script/openembedded/scripts-57556.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/scripts"

COMPATIBLE_MACHINE = "chumby-silvermoon"


do_install () {
#
# Create directories and install device independent scripts
#
    install -d ${D}/usr/chumby/scripts

    install -m 0755    ${WORKDIR}/scripts/save_time ${D}/usr/chumby/scripts
    install -m 0755    ${WORKDIR}/scripts/restore_time ${D}/usr/chumby/scripts
    install -m 0755    ${WORKDIR}/scripts/sync_time.sh ${D}/usr/chumby/scripts
    install -m 0755    ${WORKDIR}/scripts/sync_time_state.sh ${D}/usr/chumby/scripts
    install -m 0755    ${WORKDIR}/scripts/time_zone.sh ${D}/usr/chumby/scripts
}

FILES_${PN} = "/usr/chumby/scripts"

SRC_URI[md5sum] = "47c2423cac5a9f4bbc5eab0423eb0d4b"
SRC_URI[sha256sum] = "25cb9a4109344df07f553b705cfa3a850fa93b1a8cc3c9c20a48b5927eaf6a65"
