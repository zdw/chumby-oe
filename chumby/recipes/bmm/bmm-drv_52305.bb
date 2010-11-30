inherit module
DESCRIPTION = "Driver for block memory manager for video in Silvermoon"
HOMEPAGE = "http://www.chumby.com"
SECTION = "kernel/modules"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/openembedded/gst-pxa168-${PV}.tar.gz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/bmm-lib/drv"

MAKE_TARGETS = "compile"

do_install() {
        install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
        install -m 0644 bmm${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra/
}

