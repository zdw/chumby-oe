DESCRIPTION = "udev rules for Silvermoon"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/udev-58178.tar.gz \
           file://umount_repeated.sh \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src"

do_compile() {
    true
}

do_install() {
    install -d ${D}/lib/udev/rules.d
    install -m 0644 40-chumby-early.rules ${D}/lib/udev/rules.d/40-chumby-early.rules
    install -m 0644 98-chumby-late.rules ${D}/lib/udev/rules.d/98-chumby-late.rules

    install -d ${D}/usr/chumby/scripts
    install -m 0755 add-mount.sh ${D}/usr/chumby/scripts
    install -m 0755 remove-mount.sh ${D}/usr/chumby/scripts
    install -m 0755 ../umount_repeated.sh ${D}/usr/chumby/scripts
}

FILES_${PN} = "/lib/udev/rules.d/ /usr/chumby/scripts/"

SRC_URI[md5sum] = "03b3495fcfffb25c9118446f386579b3"
SRC_URI[sha256sum] = "c86d259d308290a0623337f5d160cf2026d895d1a71b1cecbaa016450bc862e9"
