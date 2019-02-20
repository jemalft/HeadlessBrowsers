FROM jenkins/jenkins:lts
# if we want to install via apt
USER root

# RUN apt-get update && apt-get install -y gconf-service libasound2 libatk1.0-0 libcairo2 libcups2 libfontconfig1 libgdk-pixbuf2.0-0 libgtk-3-0 libnspr4 libpango-1.0-0 libxss1 fonts-liberation libappindicator1 libnss3 lsb-release xdg-utils

# install maven
RUN apt-get update && apt-get install -y maven \
    && apt-get install -y git

# drop back to the regular jenkins user - good practice
USER jenkins

# Installing the plugins we need using the in-built install-plugins.sh script
RUN /usr/local/bin/install-plugins.sh git blueocean

 # Setting up environment variables for Jenkins admin user
ENV JENKINS_USER admin
ENV JENKINS_PASS admin

# Skip the initial setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Start-up scripts to set number of executors and creating the admin user
COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy
#COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/
