inherit autotools

DESCRIPTION = "Chumby http daemon library"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Duane Maxwell"
LICENSE = "GPLv2"
PR = "r0"

SRC_URI = "http://files.chumby.com/source/falconwing/build2370/chumbhttpd.tgz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/chumbhttpd"

SRC_URI[md5sum] = "cbcbf7cf0c2a10130097474914d22929"
SRC_URI[sha256sum] = "2afb717debaace0873fe4b4eb089320d40f8b22246af6bb36d72a454eff912d2"
