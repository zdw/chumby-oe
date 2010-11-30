DESCRIPTION = "Chumby image drawer"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Henry Groover"
LICENSE = "GPLv2"
PR = "r0"

DEPENDS = "giflib libpng jpeg"

SRC_URI = "http://files.chumby.com/source/silvermoon/silvermoon-1.0.1/imgtool.cpp"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}"
CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"

do_compile() {
    ${CXX} ${CFLAGS} ${LDFLAGS} imgtool.cpp -o imgtool \
           -DCNPLATFORM_${CNPLATFORM} -DCNPLATFORM=\"${CNPLATFORM}\" \
           -lpng -lgif -ljpeg
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 imgtool ${D}${bindir}
}

SRC_URI[md5sum] = "58b7435fb055c6f95aa9c5e388679687"
SRC_URI[sha256sum] = "b6dae078237fba46279a252f61308767c61d738e3cf6764cff6f3a2a70195be7"
