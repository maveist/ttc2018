package ttc2018;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;

import Changes.ModelChange;
import Changes.ModelChangeSet;
import SocialNetwork.Comment;
import SocialNetwork.Post;
import SocialNetwork.User;

public class SolutionQ2 extends Solution {
	
	private MostInfluentialCommentList topList = new MostInfluentialCommentList();
	private List<User> toRemove = new ArrayList<User>();
	

	@Override
	public String Initial() {
		// TODO Implement Q2
		
		EList<Post> listPosts = this.getSocialNetwork().getPosts();
		for(Post p : listPosts) {
				EList<Comment> listComments = p.getComments();
				for(Comment com : listComments) {
					//on parcours en profondeur tout les commentaires du post
					parcoursProfondeur(com);
				}
		}
		return topList.getMostInfluentialComments();
	}

	@Override
	public String Update(ModelChangeSet changes) {
		EList<ModelChange> coll = changes.getChanges();
		for (ModelChange change : coll) {
			change.apply();
		}
		// TODO Implement Q2
		return null;
	}
	
	// On regarde le nombre de points du commentaire, puis
	// on parcours en profondeur le commentaire.
	public void parcoursProfondeur(Comment comment) {
		
		long points = countPoints(comment);
			
	    topList.pushPost(comment, points);
		
		if( comment.getComments().isEmpty() ) { // Si il n'y a plus de fils disponible
			return;   // Je remonte d'un cran
		}		
	 
	    EList<Comment> list = comment.getComments(); // Sinon je récupère la liste des fils
	 
		  for( Comment subComment: list ) {
			  parcoursProfondeur(subComment); // Et je continue d'explorer mon arbre
		  }
	            
	}
		
	
	private long countPoints(Comment comment) {
		EList<User> likedBy = comment.getLikedBy();
		List<HashSet<User>> listStronglyConnectedUsers = new ArrayList<HashSet<User>>(); // liste qui va contenir des Set d'utilisateurs, un set correspondant à une composantes fortement connexe
		
		Iterator<User> iter = likedBy.iterator();
		
		//On parcours les personnes ayant liké le commentaire
		while(iter.hasNext()) {
			HashSet<User> stronglyConnectedUsers = new HashSet<User>();
			User currentUser = iter.next();
			if(!toRemove.contains(currentUser)) {
				stronglyConnectedUsers.add(currentUser);
				this.toRemove.add(currentUser);
				//iter.remove();
				HashSet<User> connectedFriends = hasFriendInList(currentUser,likedBy); // on recupere les users avec lesquels l'user courant est ami.
				
				
				// Si il est ami avec des gens qui ont aussi liké le commentaire, alors on regarde si a leurs tours ils sont amis avec des gens.
				if(!connectedFriends.isEmpty()) {
					for(User connectedFriend : connectedFriends) {
						profondeurFriends(connectedFriend, likedBy, stronglyConnectedUsers);
					}
				}
				
				
				listStronglyConnectedUsers.add(stronglyConnectedUsers);
			}
		}
		
		
		toRemove.clear();
		return sumStronglyConnectedGroup(listStronglyConnectedUsers);
	}
	
	
	private long sumStronglyConnectedGroup(List<HashSet<User>> listStronglyConnectedUsers) {
		long sum = 0;
		for(HashSet<User> users : listStronglyConnectedUsers) {
			sum += (users.size() * users.size());
		}
		return sum;
	}

	private void profondeurFriends(User connectedFriend, EList<User> likedBy, HashSet<User> stronglyConnectedUsers) {
		HashSet<User> connectedFriends = hasFriendInList(connectedFriend, likedBy);
		if(connectedFriends.isEmpty()) {
			stronglyConnectedUsers.add(connectedFriend);
			toRemove.add(connectedFriend);
			return;
		}
		for(User u : connectedFriends) {
			stronglyConnectedUsers.add(connectedFriend);
			toRemove.add(connectedFriend);
			profondeurFriends(u, likedBy,stronglyConnectedUsers);
		}
		
	}
	
	private HashSet<User> hasFriendInList(User oneUser, EList<User> users) {
		
		HashSet<User> friendWith = new HashSet<User>();
		
		for(User u : users) {
			if(oneUser.getFriends().contains(u) && !toRemove.contains(u)) {
				/*if(!toRemove.contains(u)) {
					
				}*/
				friendWith.add(u);
			}
		}
		
		return friendWith;
	}


	public class MostInfluentialCommentList{
		
		
		public TreeMap<Pair<Long,Date>,Comment> commentsv2;
		
		
		public MostInfluentialCommentList() {
			commentsv2 = new TreeMap<Pair<Long,Date>,Comment>(new InfluentialCommentComparator());
		}
		
		
		public void pushPost(Comment com, long nbPoints) {
			commentsv2.put(new Pair<Long,Date>(nbPoints,com.getTimestamp()), com);
			if(commentsv2.size() > 3) {
				commentsv2.pollFirstEntry();
			}
		}
		
		public String getMostInfluentialComments() {
			List<Comment> coms = new ArrayList(commentsv2.values());
			// le premier element etant le plus petit, on change de sens.
			return coms.get(2).getId()+"|"+coms.get(1).getId()+"|"+coms.get(0).getId();
		}
	}
	
	
	public class InfluentialCommentComparator implements Comparator<Pair<Long,Date>> {

		@Override
		public int compare(Pair<Long, Date> o1, Pair<Long, Date> o2) {
			
			if(o1.getFirst() > o2.getFirst()) {
				return 1;
			}else if(o1.getFirst() < o2.getFirst()) {
				return -1;
			}else {
				return o1.getSecond().compareTo(o2.getSecond());
			}
			
		}
		
	}

}