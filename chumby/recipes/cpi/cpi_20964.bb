DESCRIPTION = "Chumby control panel interface"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "bunnie"
LICENSE = "GPLv2"
PR = "r0"
PN = "cpi-${MACHINE}"

DEPENDS = "b64 libtomcrypt libtommath tomsfastmath expat"
RDEPENDS = "expat"

SRC_URI = "http://files.chumby.com/source/openembedded/cpi-20964.tar.gz;name=src \
           http://files.chumby.com/source/openembedded/cpi-include-12684.tar.gz;name=include \
           http://files.chumby.com/source/openembedded/scripts-57556.tar.gz;name=scripts \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src"
CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"


# libb64.chumby doesn't link properly, so we manually statically link it.
do_compile() {
    ${CC} -Wall -g -DLTM_DESC -DTFM_DESC -DCNPLATFORM_${CNPLATFORM} -I../include ${CFLAGS} \
          ${LDFLAGS} -pthread -lb64.chumby -lexpat -ltomcrypt -ltommath -ltfm \
           *.c cmdline/*.c ${STAGING_LIBDIR}/libb64.chumby.a -o cpi
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 cpi ${D}${bindir}

    install -d ${D}/usr/chumby/scripts
    install -m 0755 ../scripts/cpi.sh ${D}/usr/chumby/scripts
    install -m 0755 ../scripts/guidgen.sh ${D}/usr/chumby/scripts
    install -m 0755 ../scripts/chumby_version ${D}/usr/chumby/scripts
}

FILES_${PN} = "${bindir} /usr/chumby/scripts"

SRC_URI[include.md5sum] = "16898d1e46a0698c36c458ebcbdc85a1"
SRC_URI[include.sha256sum] = "c0fca789c910fe88a1f8141aa318e82e2d4d24c30faf3a1dcbdab91ea5852cde"
SRC_URI[scripts.md5sum] = "47c2423cac5a9f4bbc5eab0423eb0d4b"
SRC_URI[scripts.sha256sum] = "25cb9a4109344df07f553b705cfa3a850fa93b1a8cc3c9c20a48b5927eaf6a65"
SRC_URI[src.md5sum] = "12744b93e5ae38643c72f4b335797f6b"
SRC_URI[src.sha256sum] = "abc8fe77e5d6d95feb19444925411b0d5240d3e104a2936267bee3a09d4f87e2"

