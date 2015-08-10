mvn clean install site
chmod +x target/*.jar

OUTPUT="$(target/anagrammer-1.0.jar  Benedict Cumberbatch)"
echo "${OUTPUT}"

zip -r anagrammer.zip . -x *.git* -x *.idea*
