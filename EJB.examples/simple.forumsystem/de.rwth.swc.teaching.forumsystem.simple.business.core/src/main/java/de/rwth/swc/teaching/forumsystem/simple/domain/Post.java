/**
 * Entity implementation class for Entity: Post
 *
 * TODO Add a description to the class Post
 */
package de.rwth.swc.teaching.forumsystem.simple.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({

@NamedQuery(name = "getPostByPostIdAndThreadId", query = "SELECT post FROM Thread thread JOIN thread.posts post WHERE post.postId = :postId and thread.threadId = :threadId")

})
public class Post

{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int postId;

	private String text;

	@ManyToOne(cascade = {CascadeType.MERGE})
	private Thread thread;

	public Post() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return this.postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Thread getThread() {
		return this.thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public int getThreadId() {
		if (this.getThread() == null)
			return 0;
		return this.getThread().getThreadId();
	}

	/**
	 * Special implementation of the equals method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (Post.class.isInstance(object)) {
			if (this.getId() != 0 && ((Post) object).getId() != 0
					&& this.getId() == ((Post) object).getId()) {
				return true;
			} else {
				return (

				this.getPostId() == ((Post) object).getPostId()

				&&

				this.getThreadId() == ((Post) object).getThreadId()

				);
			}
		} else {
			return false;
		}
	}

	/**
	 * Special implementation of the hash code method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public int hashCode() {
		int hash = 1;

		hash = hash * 17 + this.getPostId();

		hash = hash * 17 + this.getThreadId();

		return hash;
	}

}
