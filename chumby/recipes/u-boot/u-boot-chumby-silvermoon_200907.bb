require u-boot.inc

PROVIDES = "virtual/bootloader"
RPROVIDES_${PN} = "virtual/bootloader"
PR = "r0"
SRCREV = "${AUTOREV}"

DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_chumby-silvermoon = "1"



SRC_URI = "http://files.chumby.com/source/openembedded/u-boot-2009.07.59129.tar.gz \
			file://0001-fix-makefile.patch \
			file://0002-fix-cmdline.patch \
			file://0003-make-cmdline-rw.patch \
"

S = "${WORKDIR}/src"

SRC_URI[md5sum] = "40ab9830b841f7b95246c756146bbe93"
SRC_URI[sha256sum] = "b7929c1d549fa7f933ad130ac589a8eaf3ca1d6182f9776b62379c71d9e3f250"
