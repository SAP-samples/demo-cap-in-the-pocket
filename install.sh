#!/bin/bash

ech "Installing SapMachine"
wget -qO- https://dist.sapmachine.io/debian/sapmachine.key | tee /etc/apt/trusted.gpg.d/sapmachine.asc > /dev/null
echo "deb https://dist.sapmachine.io/debian/$(dpkg --print-architecture)/ ./" | tee /etc/apt/sources.list.d/sapmachine.list > /dev/null
apt update -y
apt install -y sapmachine-21-jdk

echo "Installing VSCode"
wget 'https://code.visualstudio.com/sha/download?build=stable&os=linux-deb-arm64' -O code.deb
apt install -y ./code.deb
# Install the missing packages
apt -y --fix-broken install
rm code.deb

echo "Installing the VSCode plugin"
wget https://github.com/parttimenerd/cap-in-the-pocket-extension/releases/download/snapshot/cap-in-the-pocket-0.0.1.vsix .
code --install-extension cap-in-the-pocket-0.0.1.vsix --no-sandbox --user-data-dir ~
