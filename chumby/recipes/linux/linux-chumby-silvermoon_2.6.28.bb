require linux.inc

PR = "r0"
SRCREV = "${AUTOREV}"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"

DEFAULT_PREFERENCE_chumby-silvermoon = "1"


SRC_URI = "http://files.chumby.com/source/openembedded/linux-2.6.28-58169.tar.gz \
           file://0001-disable-cache-thingy.patch \
           file://defconfig \
"

S = "${WORKDIR}/src"

SRC_URI[md5sum] = "cef34374577c1e448584507cf427de3f"
SRC_URI[sha256sum] = "e08bb0b5b49b6f9050ca85d37376bd075d0d5b79266b5b1b5303ca0b47fe7ab1"
