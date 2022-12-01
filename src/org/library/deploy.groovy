package org.library

// saltstack

def SaltDeploy(hosts,command) {
    sh "salt \"${hosts}\" ${command}"
}
