package com.amuse.util;

import com.amuse.model.Plan;
import com.amuse.model.Scheme;
import com.amuse.model.Statis;
import com.amuse.model.User;

public class SubjectUtils {

    private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<User>();
    
    private static final ThreadLocal<Plan> PLAN = new ThreadLocal<Plan>();
    
    private static final ThreadLocal<Scheme> SCHEME = new ThreadLocal<Scheme>();
    
    private static final ThreadLocal<Statis> STATIS = new ThreadLocal<Statis>();

    public synchronized static void setUser(User user) {
        USER_HOLDER.set(user);
    }

    public synchronized  static User getUser() {
        return USER_HOLDER.get();
    }

    public synchronized  static void removeUser() {
        USER_HOLDER.remove();
    }
    
    public synchronized static void setPlan(Plan plan ) {
    	PLAN.set(plan);
    }

    public synchronized  static Plan getPlan() {
        return PLAN.get();
    }

    public synchronized static void removePlan() {
    	PLAN.remove();
    }
  
    
    public synchronized static void setScheme(Scheme scheme ) {
    	SCHEME.set(scheme);
    }

    public synchronized static Scheme getScheme() {
        return SCHEME.get();
    }
    
    public synchronized static void setStatis(Statis statis ) {
    	STATIS.set(statis);
    }

    public synchronized static Statis getStatis() {
        return STATIS.get();
    }
}