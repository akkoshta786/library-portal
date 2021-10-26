# list files and cp last war file to given path

sudo ls /home/chandu/capstonedeployment/com/wipro/library-portal/0.0.1-SNAPSHOT/*.war  | tail -1 | xargs sudo cp -r -t /home/chandu/capstonedeployment/wars/

# change directory
cd /home/chandu/capstonedeployment/wars/

#renaming the war file as library-portal.war

cp library-portal-*.war library-portal.war
