http://localhost:8080/Zachners/hauptmenu.xhtml

Install jdk und verwende die jre vom jdk.
Dann run as maven install muss mindestens einmal ausgeführt werden und jedesmal wenn sich die pom.xml ändert.
Sonst einfach nur Server mit stop, clean und dann debug neu starten oder es geht auch ohne neustart.

Tomcat 8 brauchst auch noch. Wo du mit Add Projekt hinzufügst. Und dann clean und dann debug.
Oder noch besesr Wildfly 8.2.0. Dazu folgende Anleitung.
http://www.mastertheboss.com/jboss-server/wildfly-8/configuring-eclipse-to-use-wildfly-8
Die Anleitung weicht ein wenig ab.

