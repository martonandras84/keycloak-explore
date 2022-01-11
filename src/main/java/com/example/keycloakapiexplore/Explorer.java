package com.example.keycloakapiexplore;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Explorer {

    private static final String SERVER_URL = "http://localhost:8081/auth";
    private static final String REALM = "master";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String CLIENT_ID = "demo-client";

    @Scheduled(initialDelay = 1000L, fixedDelay = 9999999999L)
    public void explore() {

        System.out.println("explore");



/*        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .username(USERNAME)
                .password(PASSWORD)
                .clientId(CLIENT_ID)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();*/

        Keycloak keycloak = initKeycloakWithAdminRole();

        addUser(keycloak);
        listUsers(keycloak);

        listRoles(keycloak);

        listGroups(keycloak);
    }

    Keycloak initKeycloakWithAdminRole() {
        return Keycloak.getInstance(
                SERVER_URL,
                REALM,
                USERNAME,
                PASSWORD,
                CLIENT_ID);
    }

    private void listUsers(Keycloak keycloak) {
        System.out.println("\nUSERS");
        UsersResource usersResource = keycloak.realm(REALM).users();
        for (UserRepresentation userRepresentation: usersResource.list()) {
            printUserRepresentation(userRepresentation);
        }
    }

    private void addUser(Keycloak keycloak) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("test1");
        userRepresentation.setFirstName("firstName");
        userRepresentation.setLastName("lastName");
        userRepresentation.setEmail("test@test.com");
        userRepresentation.setEnabled(true);
//        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));
        keycloak.realm(REALM).users().create(userRepresentation);
    }

    private void listRoles(Keycloak keycloak) {
        System.out.println("\nROLES");
        RolesResource rolesResource = keycloak.realm(REALM).roles();
        for (RoleRepresentation roleRepresentation: rolesResource.list()) {
            printRoleRepresentation(roleRepresentation);
        }
    }

    private void listGroups(Keycloak keycloak) {
        System.out.println("\nGROUPS");
        GroupsResource groupsResource;
        groupsResource = keycloak.realm(REALM).groups();
        for (GroupRepresentation groupRepresentation: groupsResource.groups()) {
            printGroupRepresentation(groupRepresentation);
        }
    }


    private void printUserRepresentation(UserRepresentation userRepresentation) {
        System.out.println("userName: "+userRepresentation.getUsername()+
                " userId: "+userRepresentation.getId()+
                " firstName: "+userRepresentation.getFirstName()+
                " lastName: "+userRepresentation.getLastName());
    }

    private void printRoleRepresentation(RoleRepresentation roleRepresentation) {
        System.out.println("roleName: "+roleRepresentation.getName()+
                " description: "+roleRepresentation.getDescription());
    }

    private void printGroupRepresentation(GroupRepresentation groupRepresentation) {
        System.out.println("roleName: " + groupRepresentation.getName());
    }
}
