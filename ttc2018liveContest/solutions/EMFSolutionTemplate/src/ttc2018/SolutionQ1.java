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

import Changes.AssociationCollectionInsertion;
import Changes.ChangeTransaction;
import Changes.CompositionListInsertion;
import Changes.ModelChange;
import Changes.ModelChangeSet;
import SocialNetwork.Comment;
import SocialNetwork.Post;
import SocialNetwork.User;

public class SolutionQ1 extends Solution {

	private HashMap<Integer, Pair<Integer, Post>> mapAllPosts = new HashMap<Integer, Pair<Integer, Post>>();
	
	@Override
	public String Initial() {
		// TODO Implement Q1
		EList<Post> listPosts = this.getSocialNetwork().getPosts();
		TreeMap<Pair<Integer, Post>, Post> treeMap = new TreeMap(new PostsComparator());
		for(Post post: listPosts) {
			int pts = countPointPost(post);
			Pair<Integer, Post> pair = new Pair(pts, post);
			// save all posts without sorting with their amount of points (for the update), the Post ID is the key
			mapAllPosts.put(Integer.parseInt(post.getId()), pair);
			treeMap.put(pair, post);
			// Remove an element in order to only have 3 elements in the TreeMap
			if(treeMap.size() > 3) {
				treeMap.pollFirstEntry();
			}
		}
		List<Post> ll = new ArrayList(treeMap.values());
		return ll.get(2).getId()+"|"+ll.get(1).getId()+"|"+ll.get(0).getId();
		
	}
	
	public int countPointPost(Post post) {
		int pts = 0;
		for(Comment comment: post.getComments()) {
			pts += countPointComment(comment, Integer.parseInt(post.getId()));
		}
		return pts;
	}
	
	public int countPointComment(Comment comment, int post) {
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
			updatePoints(change);
		}
		TreeMap<Pair<Integer, Post>, Post> treeMap = new TreeMap(new PostsComparator());
		// Sort Post by puting each of them in the TreeMap
		for(Pair<Integer, Post> pair: mapAllPosts.values()) {
			treeMap.put(pair, pair.getSecond());
			if(treeMap.size() > 3) {
				treeMap.pollFirstEntry();
			}
		}
		
		List<Post> ll = new ArrayList(treeMap.values());
		return ll.get(0).getId()+"|"+ll.get(1).getId()+"|"+ll.get(2).getId();
		
	}

	/**
	 * Handle the update for a ModelChange object
	 * The intent was to replicate the "dispatch" behavior that we saw with XTend
	 * @param m ModelChange update
	 */
	public void updatePoints(ModelChange m) {
		if(m.eClass().getName().equals("ChangeTransaction")) {
			ChangeTransaction change = (ChangeTransaction) m;
			updatePoints(change);
		}else if(m.eClass().getName().equals("CompositionListInsertion")){
			CompositionListInsertion change = (CompositionListInsertion) m;
			updatePoints(change);
		}else if(m.eClass().getName().equals("AssociationCollectionInsertion")) {
			AssociationCollectionInsertion changeAsso = (AssociationCollectionInsertion) m;
			updatePoints(changeAsso);
		}
	}
	
	/**
	 * Handle the update for a ChangeTransaction
	 * @param change
	 */
	public void updatePoints(ChangeTransaction change) {
		if(change.getSourceChange().eClass().getName().equals("CompositionListInsertion")) {
			CompositionListInsertion sourceChange = (CompositionListInsertion) change.getSourceChange();
			updatePoints(sourceChange);
		}else if(change.getSourceChange().eClass().getName().equals("AssociationCollectionInsertion")){
			AssociationCollectionInsertion changeAsso = (AssociationCollectionInsertion) change.getSourceChange();
			updatePoints(changeAsso);
			
		}
	}	
	
	/**
	 * Handle the update for a CompositionListInsertion
	 * @param change
	 */
	public void updatePoints(CompositionListInsertion change) {
		// See if the chang is about a comment adding
		if(change.getFeature().getName().contentEquals("comments")) {
			
			// See which object will receive the new comment
			if(change.getAffectedElement().eClass().getName().equals("Post")) {
				Post post = (Post) change.getAffectedElement();
				// Retrieve the post
				Pair<Integer, Post> pair = mapAllPosts.get(Integer.parseInt(post.getId()));
				// Update the amount of point
				pair.setFirst(pair.getFirst() + 10);
			}else if(change.getAffectedElement().eClass().getName().equals("Comment")) {
				Comment newComment = (Comment) change.getAffectedElement();
				// Retrieve the post
				Pair<Integer, Post> pair = mapAllPosts.get(Integer.parseInt(newComment.getPost().getId()));
				// Update the amount of point
				pair.setFirst(pair.getFirst()+10);
			}
		}
	}
	
	/**
	 * Handle update for a AssociationCollectionInsertion
	 * @param change
	 */
	public void updatePoints(AssociationCollectionInsertion change) {
		// See if the change is about likes
		if(change.getFeature().getName().equals("likes")) {
			Comment comment = (Comment) change.getAddedElement();
			Pair<Integer, Post> pair = mapAllPosts.get(
					comment.getPost().getId()
			);
			if(pair != null) {
				pair.setFirst(pair.getFirst()+1);
			}
			
		}
	}
	
	
	/**
	 * Comparator used for the TreeMap in order to get our TOP3 contreversial posts
	 * @author selim
	 *
	 */
	public class PostsComparator implements Comparator<Pair<Integer, Post>>{

		@Override
		public int compare(Pair<Integer, Post> o1, Pair<Integer, Post> o2) {
			// TODO Auto-generated method stub
			
			// If o1 have a greater amount of point
			if(o1.getFirst() > o2.getFirst()) {
				return 1;
			}else if(o1.getFirst() < o2.getFirst()){
				return -1;
			}else {
				// return the comparaison result of between timestamps
				return o1.getSecond().getTimestamp().compareTo(o2.getSecond().getTimestamp());
			}

		}

		
		
	}
}