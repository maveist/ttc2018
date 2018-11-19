package ttc2018;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import Changes.ModelChange;
import Changes.ModelChangeSet;
import SocialNetwork.Comment;
import SocialNetwork.Post;
import SocialNetwork.Submission;
import SocialNetwork.User;


public class SolutionQ1 extends Solution {

	@Override
	public String Initial() {
		// TODO Implement Q1
		EList<Post> listPosts = initLists();
		System.out.println(Integer.toString(listPosts.size()));
		MostControversialPostList topList = new MostControversialPostList();
		for(Post post: listPosts) {
			int nbPoints = getNbPointsPost(post);
			topList.pushPost(post, nbPoints);
		}
		return topList.getMostContreversialPosts();
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
	
	public EList<Post> initLists() {
		EList<Post> listPosts = this.getSocialNetwork().getPosts(); 
		return listPosts;
	}
	
	public int getNbPointsPost(Post post) {
		int nbComment = 0;
		HashSet<User> likerSet = new HashSet<User>();
		for(Comment comment : post.getComments()) {
			Pair<Integer, HashSet<User>> res = getNbCommentAndLike(comment);
			nbComment += res.getFirst();
			likerSet.addAll(res.getSecond());
		}
		return nbComment*10 + likerSet.size();
	}
	
	public Pair<Integer, HashSet<User>> getNbCommentAndLike(Comment comment) {
		int nbComment = 1;
		HashSet<User> likerSet = new HashSet<User>();
		for(Comment subComment: comment.getComments()) {
			Pair<Integer, HashSet<User>> res = getNbCommentAndLike(subComment);
			nbComment += res.getFirst();
			likerSet.addAll(res.getSecond());
		}
		return new Pair<Integer, HashSet<User>>(nbComment, likerSet);
	}
	
	public class MostControversialPostList{
		
		private Pair<Integer, Post>[] posts;
		public MostControversialPostList() {
			posts = new Pair[3];
		}
		
		//i<=1 && posts[i+1].getSecond().getTimestamp().compareTo(posts[i].getSecond().getTimestamp()) == -1
		
		public void pushPost(Post p, int nbPoints) {
			Pair<Integer, Post> newPair = new Pair(nbPoints, p);
			for(int i = 0; i<3;i++) {
				if(posts[i] == null) {
					posts[i] = newPair;
					break;
				}else if(posts[i].getFirst() < nbPoints && isFull()){
					posts[i] = newPair;
					break;
				}else if(posts[i].getFirst() < nbPoints && !isFull()) {
					posts[i+1] = posts[i];
				}else if(posts[i].getFirst() == nbPoints && isFull()) {
					if(p.getTimestamp().compareTo(posts[i].getSecond().getTimestamp()) == 1) {
						Pair tmp = posts[i];
						for(int j=i; j<2; j++) {
							Pair tmp1 = posts[j+1];
							posts[j+1] = tmp;
							tmp = tmp1;
						}
						posts[i] = newPair;
						break;
					}
				}else if(posts[i].getFirst() == nbPoints && !isFull()) {
					if(p.getTimestamp().compareTo(posts[i].getSecond().getTimestamp()) == 1) {
					int k = i;
					Pair tmp = posts[i];
					for(int j=i; j<nbElem()-1; j++) {
						Pair tmp1 = posts[j+1];
						posts[j+1] = tmp;
						tmp = tmp1;
					}
					posts[k] = newPair;					
					break;
					}
				}
			}
		}
		
		public int nbElem() {
			int cpt = 0;
			for(int i=0; i<3; i++) {
				if(posts[i] != null) {
					cpt++;
				}
			}
			return cpt;
		}
		
		public boolean isFull() {
			return nbElem() == 3;
		}
		
		public String getMostContreversialPosts() {
			return posts[0].getSecond().getId()+"|"+posts[1].getSecond().getId()+"|"+posts[2].getSecond().getId();
		}
	}

}
