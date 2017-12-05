/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ljug.provide.BuildService;
import ljug.provide.Services;
import util.JPAUtil;

/**
 *
 * @author pfares
 */
public class Main {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("RealmCLI_PU");
        
        Services service = BuildService.ServicesBuilder(emf);
        
        service.addUser("pascal", "pascal", null);
        service.addUser("pascal1", "pascal1", "pfares@cofares.net");
        service.addRole("admin");
        service.addRole("prof");
        service.associate("pascal", "admin");
        service.associate("pascal", "prof");
        service.associate("pascal1", "prof");
        JPAUtil.checkData("select * from NewRealm.users");
        JPAUtil.checkData("select * from NewRealm.roles");
        JPAUtil.checkData("select * from NewRealm.users_roles");
        service.dessociate("pascal1", "prof");
        JPAUtil.checkData("select * from NewRealm.users");
        JPAUtil.checkData("select * from NewRealm.roles");
        JPAUtil.checkData("select * from NewRealm.users_roles");
        service.rmUser("pascal");
        JPAUtil.checkData("select * from NewRealm.users");
        JPAUtil.checkData("select * from NewRealm.roles");
        JPAUtil.checkData("select * from NewRealm.users_roles");
        /*
        EntityManager em = emf.createEntityManager();
        
        RolesJpaController tc = new RolesJpaController(emf);
        UsersJpaController uc = new UsersJpaController(emf);
        
        uc.create(new Users("pascal","pascal"));
        tc.create(new Roles("chef"));
        
        uc.findUsersEntities().stream().forEach(System.out::println);
        tc.findRolesEntities().stream().forEach(System.out::println);
        
        Users pascal = uc.findUsers("pascal");
        Roles chef = tc.findRoles("chef");
        System.out.println("-----------\n\n");
        System.out.printf("%s %d\n",pascal,pascal.getRolesSet().size());
        pascal.getRolesSet().stream().forEach(System.out::println);
        System.out.println("-----------\n\n");
        Set<Users> lr = chef.getUsersSet();
        System.out.printf("LR %s %d\n",chef,lr.size());
        lr.stream().forEach(u -> System.out.println("Heho"+u));
        System.out.println("----------------------------------------------------\n\n");
        pascal.getRolesSet().add(chef);
        //FonctionsDiverses.addIfNot(pascal.getRolesList(), chef);
        //pascal.getRolesList().add(chef);
        //chef.getUsersList().add(pascal);
        chef.getUsersSet().add(pascal);
        //FonctionsDiverses.addIfNot(chef.getUsersList(),pascal);
        //edit du propriétaire de la relation
        //uc.edit(pascal);
        System.out.println("----------------------------------------------------\n\n");
        tc.edit(chef);
        
        uc.create(new Users("pascal1","pascal1"));
        tc.create(new Roles("prof"));
        
        Users pascal1 = uc.findUsers("pascal1");
        Roles prof = tc.findRoles("prof");
        
        pascal.getRolesSet().add(prof);
        prof.getUsersSet().add(pascal);
        uc.edit(pascal);
        pascal1.getRolesSet().add(prof);
        prof.getUsersSet().add(pascal1);
        uc.edit(pascal1);
        */
        
    }
}
