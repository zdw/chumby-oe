DESCRIPTION = "Chumby network scripts"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://files.chumby.com/script/openembedded/scripts-57556.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/scripts"


do_install () {
    install -d ${D}/usr/chumby/scripts
    install -m 0755 ${WORKDIR}/scripts/start_network ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/network_adapter_list.sh ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/network_interface ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/network_running.sh ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/network_status.sh ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/signal_strength ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/BDXML.pm ${D}/usr/chumby/scripts/
    install -m 0755 ${WORKDIR}/scripts/macgen.sh ${D}/usr/chumby/scripts/

    install -d ${D}${sysconfdir}
    ln -sf /psp/resolv.conf ${D}${sysconfdir}/resolv.conf
}

FILES_${PN} = "/usr/chumby/scripts ${sysconfdir}"

SRC_URI[md5sum] = "47c2423cac5a9f4bbc5eab0423eb0d4b"
SRC_URI[sha256sum] = "25cb9a4109344df07f553b705cfa3a850fa93b1a8cc3c9c20a48b5927eaf6a65"
