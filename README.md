# bilionix-core

Persistence core package used as a replacement of JPA and EJB boilerplate code in EAR projects.
Project is based on Maven builder.

### Maven artifacts

* com.bilionix.core : bilionix-core - parent artifact;
* com.bilionix.core : bilionix-core-interfaces  - interfaces for Entities and EJBs;
* com.bilionix.core : bilionix-core-persistence - implementations of generic @MappedSuperclass Entities and EJBs with @PersistenceContext injection.

### Provided packages

* com.bilionix.core.interfaces;
* com.bilionix.core.persistence.

### Interfaces usage

To use interfaces as external GWT module you should:

1. Add maven dependency to `bilionix-core-interfaces` artifact;
2. Use `compileSourcesArtifacts` parameter in your `maven-gwt-compiler` plugin.

Sample pom.xml:

    <dependencies>
        <dependency>
            <groupId>com.bilionix.core</groupId>
                <artifactId>bilionix-core-interfaces</artifactId>
                <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>gwt-maven-plugin</artifactId>
                <version>2.5.0</version>
                <configuration>
                    <compileSourcesArtifacts>
                        <compileSourcesArtifact>com.bilionix.core:bilionix-core-interfaces</compileSourcesArtifact>
                    </compileSourcesArtifacts>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
See official docs for more information: http://mojo.codehaus.org/gwt-maven-plugin/compile-mojo.html#compileSourcesArtifacts


Then you should inherit your GWT module:

    <module>
        <inherits name="com.bilionix.core.interfaces.Interfaces"/>
        <!-- ... -->
    </module>

### Persistence usage

To depend on this project you have to create persistence unit and share in your EAR project as described:
http://stackoverflow.com/questions/4073635/sharing-a-persistence-unit-across-components-in-a-ear-file/14971161#14971161


To create entity in your persistence unit or ejb-module just extend PersistableEntityImpl:

    @Entity
    @Table(name="user")
    public class User extends PersistableEntityImpl {
        @NotBlank
        private String login;

        @NotBlank
        private String password;

        ... getters/setters ...
    }

To create EJB manager for entity you should extend PersistableEntityManager and PersistableEntityManagerImpl:

    @Remote
    public interface UserEjb extends PersistableEntityManager<User> {
        User login(String login, String password);
    }

    @Stateless
    @Remote(UserEjb.class)
    public class UserEjbImpl extends PersistableEntityManagerImpl<User> implements UserEjb {
        public User login(String login, String password) {
            User result;

            String q = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password";
            TypedQuery<User> query = em.createQuery(q, User.class)
                                   .setParameter("login", login)
                                   .setParameter("password", password);

            try {
                result = query.setMaxResults(1).getSingleResult();
            }
            catch (NoResultException e) {
                result = null;
            }

            return result;
        }
    }

There are also helpful generic interfaces and implementations for named-entities and reference-entities in the packages.

### Contacts

Viacheslav Dobromyslov viacheslav@dobromyslov.ru
