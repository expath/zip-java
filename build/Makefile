ZIP.ver  := 0.2.0
ZIP.mod  := expath-zip-java
PKG.root := $(ZIP.mod)-$(ZIP.ver)

JAVA.dir := ../zip-java
JAVA.jar := $(JAVA.dir)/dist/zip-java.jar

all: clean dist

dist:
	( cd $(JAVA.dir)/ && ant )
	mkdir "$(PKG.root)"
	echo "Version: $(ZIP.ver)" > "$(PKG.root)/VERSION"
	echo "Subversion revision:" `svnversion` >> "$(PKG.root)/VERSION"
	cp "$(JAVA.jar)" "$(PKG.root)/expath-zip-java.jar"
#	cp README "$(PKG.root)"
	cp -pPR "../zip-java/src" "$(PKG.root)"
	find "$(PKG.root)/src" -type d \( -name .svn -o -name .~ \) \
	    -exec rm -rf {} \; 2>/dev/null || true
	zip -r "$(PKG.root).zip" "$(PKG.root)"
	rm -rf "$(PKG.root)"

clean:
	rm -rf "$(PKG.root)"
	rm -rf "$(PKG.root).zip"

.PHONY: dist pkg clean
