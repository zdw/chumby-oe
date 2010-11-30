DESCRIPTION = "Chumby Daughter Card ID interface"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "bunnie"
LICENSE = "GPLv2"
PR = "r0"
PN = "dcid-${MACHINE}"

DEPENDS = "b64 libtomcrypt libtommath tomsfastmath expat"
RDEPENDS = "expat"

SRC_URI = "http://files.chumby.com/source/openembedded/dcid-include-500.tar.gz;name=include \
           http://files.chumby.com/source/openembedded/dcid-17020.tar.gz;name=dcid \
           http://files.chumby.com/source/openembedded/scripts-57556.tar.gz;name=scripts \
           file://chumby_accel.h \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src"
CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"


# libb64.chumby doesn't link properly, so we manually statically link it.
do_compile() {
    ${CC} -Wall -g -DCNPLATFORM_${CNPLATFORM} -I.. -I../include ${CFLAGS} \
          ${LDFLAGS} \
          *.c main/*.c -o dcid
    ${CC} -Wall -g -DCNPLATFORM_${CNPLATFORM} -I.. -I../include ${CFLAGS} \
          -DDCID_ALLOW_WRITE ${LDFLAGS} \
          *.c main/*.c -o dcid-write-enabled
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 dcid ${D}${bindir}
    install -m 0755 dcid-write-enabled ${D}${bindir}

    install -d ${D}/usr/chumby/scripts
    install -m 0755 ../scripts/dcid_getparms ${D}/usr/chumby/scripts
}

FILES_${PN} = "${bindir}/dcid /usr/chumby/scripts"
FILES_${PN}-write-enabled = "${bindir}/dcid-write-enabled"

SRC_URI[include.md5sum] = "5e69cf34543726aac9609d822280648f"
SRC_URI[include.sha256sum] = "e86c71b07fe1644c3ec737ad5ad9a077232319bba85952c275652238dd150fc8"
SRC_URI[dcid.md5sum] = "e9dbe3566028c1c494544b808772d536"
SRC_URI[dcid.sha256sum] = "353806108fda2e65ca7a1e1482c83c3093d64f7b6a92a4f04345e593f6b59f6c"
SRC_URI[scripts.md5sum] = "47c2423cac5a9f4bbc5eab0423eb0d4b"
SRC_URI[scripts.sha256sum] = "25cb9a4109344df07f553b705cfa3a850fa93b1a8cc3c9c20a48b5927eaf6a65"
