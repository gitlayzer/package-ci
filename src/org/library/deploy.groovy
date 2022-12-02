package org.library

// saltstack

def SaltDeploy(hosts,command) {
    sh "salt \"${hosts}\" ${command}"
}


// Ansible
def AnsibleDeploy(hosts,command) {
    sh "ansible ${command} ${hosts}"
}
