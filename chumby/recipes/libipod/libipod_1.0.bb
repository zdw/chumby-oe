inherit autotools

DESCRIPTION = "Basic no-frills ipod library"
HOMEPAGE = "http://www.chumby.com/"
AUTHOR = "Duane Maxwell"
LICENSE = "GPLv2"
PR = "r0"

DEPENDS = "expat jpeg libpng tiff zlib"

CONFIGUREOPTS += " \
  --enable-plist \
  --enable-jpeg \
  --enable-png \
  --enable-tiff \
"


SRC_URI = "http://files.chumby.com/source/falconwing/build2370/libipod.tgz"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/libipod"

SRC_URI[md5sum] = "05fc847fd04175cee23f0ce62c4d5e6f"
SRC_URI[sha256sum] = "a39d1f43781fba9df85549ffd2ff07d7d4542f5d4d6dc6bdca56f93a670c346e"
