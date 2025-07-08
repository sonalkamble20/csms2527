import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

const Profile = () => {
  const [username, setUsername] = useState("");
  const [posts, setPosts] = useState([]);
  const [newPost, setNewPost] = useState("");

  useEffect(() => {
    // Get username from localStorage
    const storedUsername = localStorage.getItem("username");
    if (storedUsername) {
      setUsername(storedUsername);
    }
    
    // Fetch user's posts from backend here (optional, dummy data for now)
    // Replace with your fetchData call if you want
    setPosts([
      { id: 1, content: "My first post" },
      { id: 2, content: "Another post" },
    ]);
  }, []);

  const onChange = (e) => setNewPost(e.target.value);

  const onSubmit = (e) => {
    e.preventDefault();
    if (!newPost.trim()) return;

    // Add the new post locally for now
    setPosts([...posts, { id: posts.length + 1, content: newPost }]);
    setNewPost("");

    // TODO: Send new post to backend using fetchData if you want
  };

  return (
    <div className="container mt-4">
      <h2>Welcome, {username}</h2>

      <h4>Your Posts</h4>
      <ul className="list-group mb-3">
        {posts.map(post => (
          <li key={post.id} className="list-group-item">{post.content}</li>
        ))}
      </ul>

      <form onSubmit={onSubmit}>
        <div className="mb-3">
          <label htmlFor="newPost" className="form-label">Create a new post</label>
          <input
            type="text"
            className="form-control"
            id="newPost"
            value={newPost}
            onChange={onChange}
            placeholder="Write something..."
          />
        </div>
        <button className="btn btn-primary" type="submit">Add Post</button>
      </form>
    </div>
  );
};

export default Profile;
