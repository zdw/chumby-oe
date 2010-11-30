inherit autotools

DESCRIPTION = "Chumby ipod web server"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Duane Maxwell"
LICENSE = "GPLv2"
PR = "r0"

DEPENDS = "chumbhttpd libipod"
RDEPENDS = "chumbhttpd"

SRC_URI = "http://files.chumby.com/source/falconwing/build2370/chumbipodd.tgz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/chumbipodd"

SRC_URI[md5sum] = "cdb07989c5d8f2edb0a7d46b298fdc36"
SRC_URI[sha256sum] = "3b5b1c6707826368ce760adf9da768dbefa6baa8874d1a5594236529ca3d64ff"
