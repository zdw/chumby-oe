DESCRIPTION = "Chumby basic init scripts"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPL"
PR = "r0"

CNPLATFORM = "${@bb.data.getVar('MACHINE', d, 1).replace('chumby-', '')}"

SRC_URI = "http://files.chumby.com/source/openembedded/partitions-2.0-58301.tar.gz;name=parts \
           http://files.chumby.com/source/openembedded/partitions-silvermoon-59026.tar.gz;name=silvermoon \
           file://0001-force-mount-all.patch \
           file://0002-set-regioncode.patch \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/src/rfs1/etc"

COMPATIBLE_MACHINE = "(chumby-silvermoon|chumby-falconwing)"


do_compile () {
    echo '#!/usr/bin/perl'                                             > hwconfig.pl
    echo '# $Id: hwconfig.pl 8544 2009-05-11 19:09:17Z henry $'       >> hwconfig.pl
    echo '# Perl globals for hardware config'                         >> hwconfig.pl
    echo '#'                                                          >> hwconfig.pl
    echo '# Usage:'                                                   >> hwconfig.pl
    echo '# require "/etc/hwconfig.pl";'                              >> hwconfig.pl
    echo ''                                                           >> hwconfig.pl
    echo '$CNPLATFORM="'${CNPLATFORM}'";'                             >> hwconfig.pl
    echo '$HAS_CRYPTO=$ENV{"HAS_CP"};'                                >> hwconfig.pl
    echo '$USB_TESTDIR="/sys/devices/platform/pxa168-ehci/usb1/1-1";' >> hwconfig.pl
    echo '$NETWORKIF="wlan0";'                                        >> hwconfig.pl
    echo ''                                                           >> hwconfig.pl
    echo 'if ( -f "/psp/hwconfig.pl")'                                >> hwconfig.pl
    echo '{'                                                          >> hwconfig.pl
    echo '	require "/psp/hwconfig.pl";'                          >> hwconfig.pl
    echo '}'                                                          >> hwconfig.pl
    echo ''                                                           >> hwconfig.pl
    echo 'return 1;'                                                  >> hwconfig.pl
    echo ''                                                           >> hwconfig.pl

    echo '#!/bin/sh'                                                   > hwconfig
    echo '# $Id: hwconfig 8544 2009-05-11 19:09:17Z henry $'          >> hwconfig
    echo '# platform-specific hardware config'                        >> hwconfig
    echo 'CNPLATFORM="'${CNPLATFORM}'"'                               >> hwconfig
    echo 'HAS_CRYPTO=${HAS_CP:-0}'                                    >> hwconfig
    echo 'NETWORKIF=wlan0'                                            >> hwconfig
}

do_install () {
#
# Create directories and install device independent scripts
#
    install -d ${D}/proc
    install -d ${D}/sys
    install -d ${D}/mnt
    install -d ${D}/var
    install -d ${D}/tmp
    install -d ${D}/usr/chumby/scripts
    install -d ${D}/root

    install -d ${D}${sysconfdir}/init.d

    install -m 0755    ${WORKDIR}/src/rfs1/etc/init.d/rcS               ${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/src/rfs1/etc/init.d/rcS.background    ${D}${sysconfdir}/init.d
    install -m 0644    ${WORKDIR}/src/rfs1/etc/inittab                  ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/fstab                    ${D}${sysconfdir}
    cp -af             ${WORKDIR}/src/rfs1/etc/mtab                     ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/issue                    ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/motd                     ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/shells                   ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/bashrc                   ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/profile                  ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/csh.cshrc                ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/csh.login                ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/host.conf                ${D}${sysconfdir}
    cp -af             ${WORKDIR}/src/rfs1/etc/hostname                 ${D}${sysconfdir}
    cp -af             ${WORKDIR}/src/rfs1/etc/hosts                    ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/hosts.allow              ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/hosts.deny               ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/inputrc                  ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/src/rfs1/etc/ld.so.conf               ${D}${sysconfdir}
    cp -af             ${WORKDIR}/src/rfs1/etc/ld.so.preload            ${D}${sysconfdir}

    cp -af             ${WORKDIR}/src/rfs1/etc/timezone                 ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/passwd                   ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/shadow                   ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/group                    ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/nsswitch.conf            ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/pwdb.conf                ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/services                 ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/protocols                ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/DIR_COLORS               ${D}${sysconfdir}
    install -m 0644    ${WORKDIR}/src/rfs1/etc/DIR_COLORS.xterm         ${D}${sysconfdir}

    install -m 0644    ${WORKDIR}/src/rfs1/etc/sysctl.conf              ${D}${sysconfdir}

    install -m 0755    ${WORKDIR}/src/rfs1/usr/chumby/scripts/create_storage_partition.sh ${D}/usr/chumby/scripts

    # Add in custom video sizing
    echo "export VIDEO_X_RES=${MACHINE_DISPLAY_WIDTH_PIXELS}"                                >> ${D}${sysconfdir}/profile
    echo "export VIDEO_Y_RES=${MACHINE_DISPLAY_HEIGHT_PIXELS}"                               >> ${D}${sysconfdir}/profile
    echo "export VIDEO_RES=${MACHINE_DISPLAY_WIDTH_PIXELS}x${MACHINE_DISPLAY_HEIGHT_PIXELS}" >> ${D}${sysconfdir}/profile
    echo "export CNPLATFORM=${CNPLATFORM}"                                                   >> ${D}${sysconfdir}/profile
    echo "export HAS_CP=${MACHINE_HAS_CRYPTOPROCESSOR}"                                      >> ${D}${sysconfdir}/profile

    install -m 0755   hwconfig.pl ${D}${sysconfdir}
    install -m 0755   hwconfig    ${D}${sysconfdir}

    ln -sf /mnt/storage/psp ${D}/psp
    ln -sf /mnt             ${D}/media
}

FILES_${PN} = "/etc /proc /sys /tmp /psp /mnt /var /media /usr/chumby/scripts /root"

SRC_URI[parts.md5sum] = "9a217adbe77c43c97e7e4c959ec16439"
SRC_URI[parts.sha256sum] = "db94b9d3b4bd87b8712c407575019d29f7a0563c241261a29454de2cc1bece7a"
SRC_URI[silvermoon.md5sum] = "180e52fefb842d9ad4c47f585aaabe0d"
SRC_URI[silvermoon.sha256sum] = "d3e85e70f9f5ad06633915e910ef11bc4d5c4cd0a9c73dcb6c58c1187fe320dd"
