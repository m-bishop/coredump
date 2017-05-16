/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.coredumpproject.ChatEndPoint;

import com.coredumpproject.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author Arun Gupta
 */
@ServerEndpoint("/chat")
public class ChatEndpoint {

    //@PersistenceContext(name = "dbtest")
    //private EntityManager em;

    @OnOpen
    public void open(Session session) {
        System.out.println("Session Opened ... Creating entity.");
        createEntity();
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        System.out.println("message: " + message);
        for (Session peer : client.getOpenSessions()) {
            peer.getBasicRemote().sendText(message);
        }
    }

    private void createEntity(){

        try{
        		/* Create EntityManagerFactory */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbtest");

		/* Create and populate Entity */
            Employee employee = new Employee();
            employee.setFirstname("Gregory");
            employee.setLastname("Bishop");
            employee.setEmail("coredumpproject@gmail.com");
            employee.setIdEmployee(1);

            System.out.println("employee created");

		/* Create EntityManager */
            EntityManager em = emf.createEntityManager();

		/* Persist entity */
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("employee persisted");

		/* Retrieve entity */
            employee = em.find(Employee.class, 1);
            System.out.println(employee);
            System.out.println("employee retrieved");

		/* Update entity
        em.getTransaction().begin();
        employee.setFirstname("Pranil");
        System.out.println("Employee after updation :- " + employee);
        em.getTransaction().commit();

		 /* Remove entity
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();

		/* Check whether enittiy is removed or not
        employee = em.find(Employee.class, 1);
        System.out.println("Employee after removal :- " + employee);
        */
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
