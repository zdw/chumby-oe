inherit module-base
DESCRIPTION = "GST Plugins for Silvermoon"
HOMEPAGE = "http://www.chumby.com"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "${CHUMBY_SVN_HOST}/firmware/gst_pxa168-1.0/trunk;proto=https;module=src"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/gstreamer"

COMPATIBLE_MACHINE = "chumby-silvermoon"

DEPENDS = "gstreamer"

do_compile() {
    oe_runmake MAKERULES="../rules.make" \
     CFLAGS="${CFLAGS} `pkg-config gstreamer --cflags` \
        -I${STAGING_KERNEL_DIR}/arch/arm/mach-mmp/include \
	-I${S}/src/gst_plugin/include/ \
	-I${S}/gst/gst_include \
	-I${S}/../ipp/ipp_include \
	-L${S}/../ipp/lib \
	-L${S}/gst/gst/lib \
	-L${S}/gst/gstdeps/lib \
	-DHAVE_CONFIG_H -fPIC \
        -DBMM_ENABLE" \
        compile
}

do_install() {
        install -d ${D}${libdir}
        install -m 0755 libcodecaacdec.so ${D}${libdir}
        install -m 0755 libcodecgifdec.so ${D}${libdir}
        install -m 0755 libcodech264dec.so ${D}${libdir}
        install -m 0755 libcodech264enc.so ${D}${libdir}
        install -m 0755 libcodecjpegdec.so ${D}${libdir}
        install -m 0755 libcodecjpegenc.so ${D}${libdir}
        install -m 0755 libcodecmidi.so ${D}${libdir}
        install -m 0755 libcodecmp3dec.so ${D}${libdir}
        install -m 0755 libcodecmpeg4dec.so ${D}${libdir}
        install -m 0755 libcodecmpeg4enc.so ${D}${libdir}
        install -m 0755 libcodecpngdec.so ${D}${libdir}
        install -m 0755 libcodecwmadec.so ${D}${libdir}
        install -m 0755 libcodecwmvdec.so ${D}${libdir}
        install -m 0755 libcodech263dec.so ${D}${libdir}
        install -m 0755 libcodech263enc.so ${D}${libdir}
        install -m 0755 libippcam.so ${D}${libdir}
        install -m 0755 libippie.so ${D}${libdir}
        install -m 0755 libmiscgen.so ${D}${libdir}
}

PACKAGES = "\
    ipp-aac-dec \
    ipp-gif-dec \
    ipp-h264-dec \
    ipp-h264-enc \
    ipp-jpeg-dec \
    ipp-jpeg-enc \
    ipp-mp3-dec \
    ipp-mpeg4-dec \
    ipp-mpeg4-enc \
    ipp-png-dec \
    ipp-wma-dec \
    ipp-wmv-dec \
    ipp-h263-dec \
    ipp-h263-enc \
    ipp-cam \
    ipp-ie \
    ipp-midi \
    ipp-miscgen \
"

FILES_ipp-aac-dec = "${libdir}/libcodecaacdec.so"
FILES_ipp-gif-dec = "${libdir}/libcodecgifdec.so"
FILES_ipp-h264-dec = "${libdir}/libcodech264dec.so"
FILES_ipp-h264-enc = "${libdir}/libcodech264enc.so"
FILES_ipp-jpeg-dec = "${libdir}/libcodecjpegdec.so"
FILES_ipp-jpeg-enc = "${libdir}/libcodecjpegenc.so"
FILES_ipp-mp3-dec = "${libdir}/libcodecmp3dec.so"
FILES_ipp-mpeg4-dec = "${libdir}/libcodecmpeg4dec.so"
FILES_ipp-mpeg4-enc = "${libdir}/libcodecmpeg4enc.so"
FILES_ipp-png-dec = "${libdir}/libcodecpngdec.so"
FILES_ipp-wma-dec = "${libdir}/libcodecwmadec.so"
FILES_ipp-wmv-dec = "${libdir}/libcodecwmvdec.so"
FILES_ipp-h263-dec = "${libdir}/libcodech263dec.so"
FILES_ipp-h263-enc = "${libdir}/libcodech263enc.so"
FILES_ipp-midi = "${libdir}/libcodecmidi.so"
FILES_ipp-cam = "${libdir}/libippcam.so"
FILES_ipp-ie = "${libdir}/libippie.so"
FILES_ipp-miscgen = "${libdir}/libmiscgen.so"
