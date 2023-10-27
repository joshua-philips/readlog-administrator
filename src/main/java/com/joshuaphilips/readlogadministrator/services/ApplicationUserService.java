package com.joshuaphilips.readlogadministrator.services;

import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.joshuaphilips.readlogadministrator.models.ApplicationUser;

@Service
public class ApplicationUserService implements UserDetailsService {
    private Firestore firestore;

    public ApplicationUserService() {
        firestore = FirestoreClient.getFirestore(FirebaseApp.getInstance());

    }

    public ApplicationUser getAdminUser(String email) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> future = firestore.collection("admin").document(email).get();
        DocumentSnapshot document = future.get();
        ApplicationUser user = document.toObject(ApplicationUser.class);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApplicationUser user = getAdminUser(username);
            return user;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found: " + username);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

}
