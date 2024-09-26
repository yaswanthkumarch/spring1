package com.jsp.swastha.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.IdNotPresentException;
import com.jsp.swastha.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo userrepo;

	public User saveUser(User user) {
		return userrepo.save(user);
	}

	public User fetchByEmail(String email) {
		User db = userrepo.fetchByEmail(email);
		if (db != null) {
			return db;
		} else {
			return null;
		}
	}

	public User updatepwd(User user) {
		User db = this.fetchbyId(user.getId());
		if (db != null) {
			if (user.getPassword() != null) {
				db.setPassword(user.getPassword());
			} 
			return userrepo.save(db);
		} else 
			return null;
		
	}

	public User update(User user) {
		User db = this.fetchbyId(user.getId());
		if (db != null) {
			if (user.getPassword() != null) {
				db.setPassword(user.getPassword());
			}
			if (user.getAvailabilty() != null) {
				db.setAvailabilty(user.getAvailabilty());
			}
			if (user.getBloodGroup() != null) {
				db.setBloodGroup(user.getBloodGroup());
			}
			if (user.getEmail() != null) {
				db.setEmail(user.getEmail());
			}
			if (user.getFirstName() != null) {
				db.setFirstName(user.getFirstName());
			}
			if (user.getGender() != null) {
				db.setGender(user.getGender());
			}
			if (user.getLastName() != null) {
				db.setLastName(user.getLastName());
			}
			if (user.getPhone() != 0) {
				db.setPhone(user.getPhone());
			}
			if (user.getAddress() != null) {
				db.setAddress(user.getAddress());
			}
			if (user.getSpecialists() != null) {
//				List<Specialist> splist = new ArrayList<Specialist>();
//				splist.addAll(user.getSpecialists());
//				splist.addAll(db.getSpecialists());
//				db.setSpecialists(splist);
				db.setSpecialists(user.getSpecialists());
			}
			if (user.getPayments() != null) {
				db.setPayments(user.getPayments());
			}
			return userrepo.save(db);
		} else
			return null;
	}

	public User fetchbyId(String id) {
		java.util.Optional<User> db = userrepo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			throw new IdNotPresentException();
		}
	}

//	public User findById(String id) {
//		User db = userrepo.findById(id).get();
//		if (db != null) {
//			return db;
//		} else {
//			return null;
//		}
//	}

//	public User deletebyId(String id) {
//		User db = userrepo.findById(id).get();
//		if (db == null) {
//			return null;
//		} else {
//			User a = userrepo.findById(id).get();
//			userrepo.deleteById(id);
//			return a;
//		}
//	}
	public User deleteById(String id) {
		java.util.Optional<User> db = userrepo.findById(id);
		if (db.isEmpty()) {
			throw new IdNotPresentException();
		} else {
			userrepo.deleteById(id);
			return db.get();
		}
	}

	public List<User> fetchByBloodgroup(String bloodGroup) {
		return userrepo.fetchByBloodgroup(bloodGroup);
	}

	public List<User> fetchAllBloodGroup() {
		return userrepo.fetchAllBloodGroup();
	}

}
