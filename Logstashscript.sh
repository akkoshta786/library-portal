#Download and install the Public Signing Key

wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -

# You may need to install the apt-transport-https package on Debian before proceeding:

sudo apt-get install apt-transport-https


# Save the repository definition to /etc/apt/sources.list.d/elastic-7.x.list

echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-7.x.list

# Run sudo apt-get update and the repository is ready for use. You can install it with

sudo apt-get update && sudo apt-get install logstash

# Enable service to run on startup

sudo systemctl enable logstash.service
