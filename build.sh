mvn clean install site
chmod +x target/*.jar

OUTPUT="$(target/anagram-1.0-SNAPSHOT.jar  Benedict Cumberbatch)"
echo "${OUTPUT}"