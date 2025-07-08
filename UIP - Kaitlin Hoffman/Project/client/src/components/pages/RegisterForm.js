import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './Form.css';
import { fetchData } from "../../main.js";
import { useState } from "react";

const RegisterForm = () => {

  const[user, setUser] = useState({
    username: '',
    email: ' ',
    password: ' '
  });

  const {username, password1, password2} = user;

  const onChange =(e) => setUser({...user,  [e.target.name]: e.target.value})

const onSubmit = (e) => {
  e.preventDefault();

  fetchData("/user/register", user, "POST").then((data) => {
    if(!data.message) {
      console.log(data)
    }
  }).catch((error) => {
    console.log(error)
  })


}
    return(
      <div>
        <form onSubmit={onSubmit}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username</label>
            <input 
              type="text" 
              className="form-control" 
              id="username"
              name='username'
              onChange={onChange}
              value={username}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input 
              type="password" 
              className="form-control" 
              id="password"
              name='password'
              onChange={onChange}
              value={password1}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password2" className="form-label">Confirm Password</label>
            <input 
              type="password" 
              className="form-control" 
              id="password2"
              name='password2'
              onChange={onChange}
              value={password2}
              required
            />
          </div>
          <input type="submit" className="btn btn-primary" value="Register"/>
        </form>
      </div>
    );
  }

  export default RegisterForm;