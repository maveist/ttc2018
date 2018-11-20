package ttc2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.emf.common.util.EList;

import Changes.ChangeTransaction;
import Changes.CompositionListInsertion;
import Changes.ModelChange;
import Changes.ModelChangeSet;
import SocialNetwork.Comment;
import SocialNetwork.Post;
import SocialNetwork.User;

public class SolutionQ1 extends Solution {

	private HashMap<Integer, Pair<Integer, Post>> mapAllPosts = new HashMap<Integer, Pair<Integer, Post>>();
	private HashMap<Integer, Integer> mapAllComments = new HashMap<Integer, Integer>();
	
	@Override
	public String Initial() {
		// TODO Implement Q1
		EList<Post> listPosts = this.getSocialNetwork().getPosts();
		List<Pair<Integer, Post>> resultsPosts = new ArrayList<Pair<Integer, Post>>();
		TreeMap<Pair<Integer, Post>, Post> treeMap = new TreeMap(new PostsComparator());
		for(Post post: listPosts) {
			int pts = countPointPost(post);
			Pair<Integer, Post> pair = new Pair(pts, post);
			mapAllPosts.put(Integer.parseInt(post.getId()), pair);
			treeMap.put(pair, post);
			if(treeMap.size() > 3) {
				treeMap.pollFirstEntry();
			}
		}
		List<Post> ll = new ArrayList(treeMap.values());
		return ll.get(0).getId()+"|"+ll.get(1).getId()+"|"+ll.get(2).getId();
		
	}
	
	public int countPointPost(Post post) {
		int pts = 0;
		for(Comment comment: post.getComments()) {
			pts += countPointComment(comment, Integer.parseInt(post.getId()));
		}
		return pts;
	}
	
	public int countPointComment(Comment comment, int post) {
		mapAllComments.put(Integer.parseInt(comment.getId()), post);
		int pts = 10 + comment.getLikedBy().size();
		for(Comment subComment: comment.getComments()) {
			pts += countPointComment(subComment, post);
		}
		return pts;
	}

	@Override
	public String Update(ModelChangeSet changes) {
		EList<ModelChange> coll = changes.getChanges();
		for (ModelChange change : coll) {
			change.apply();
		}
		// TODO Implement Q1
		return null;
	}


	public void updatePoints(ModelChange m) {
		if(m.eClass().getName().equals("ChangeTransaction")) {
			ChangeTransaction change = (ChangeTransaction) m;
			if(change.getSourceChange().eClass().getName().equals("CompositionListInsertion")) {
				CompositionListInsertion sourceChange = (CompositionListInsertion) change.getSourceChange();
				if(sourceChange.getAffectedElement().eClass().getName().contentEquals("Post")) {
					Post post = (Post) sourceChange.getAffectedElement();
					Pair<Integer, Post> pair = mapAllPosts.get(Integer.parseInt(post.getId()));
					pair.setFirst(pair.getFirst() + 10);
				}
			}
		}	
		
	}
	
	public void updatePoints(CompositionListInsertion change) {
		if(change.getAffectedElement().eClass().getName().equals("Post")) {
			Post post = (Post) change.getAffectedElement();
			Pair<Integer, Post> pair = mapAllPosts.get(Integer.parseInt(post.getId()));
			pair.setFirst(pair.getFirst() + 10);
		}else if(change.getAffectedElement().eClass().getName().equals("Comment")) {
			Pair<Integer, Post> pair = mapAllPosts.get(
					mapAllComments.get(
							Integer.parseInt(((Comment) change.getAffectedElement()).getId())
					)
			);
			
			pair.setFirst(pair.getFirst()+10);
		}
	}
	
	public class PostsComparator implements Comparator<Pair<Integer, Post>>{

		@Override
		public int compare(Pair<Integer, Post> o1, Pair<Integer, Post> o2) {
			// TODO Auto-generated method stub
			
			if(o1.getFirst() > o2.getFirst()) {
				return 1;
			}else if(o1.getFirst() < o2.getFirst()){
				return -1;
			}else {
				return o1.getSecond().getTimestamp().compareTo(o2.getSecond().getTimestamp());
			}

		}

		
		
	}
}