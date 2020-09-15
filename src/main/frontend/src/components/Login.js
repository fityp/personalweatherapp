import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import '../styles/login.scss'

const Login = () => {
     const [username, setUsername] = useState("");
     const [password, setPassword] = useState("");
     // const [loading, setLoading] = useState(false);

     const handleSubmit = (e) => {
          e.preventDefault();
          console.log(username)
          console.log(password)
          console.log("Form submitted..")
     }

     return (
          <section id="login">
               <section id="login-container">
                    <div id="login-title">Login</div>
                    <form id="login-form" onSubmit={handleSubmit}>
                         <input
                              className="login-input"
                              type="text"
                              name="username"
                              placeholder="Username"
                              onChange={e => setPassword(e.target.value)}
                         />
                         <input
                              className="login-input"
                              type="password"
                              name="password"
                              placeholder="Password"
                              onChange={e => setUsername(e.target.value)}
                         />
                         <button
                              id="login-button"
                              type="submit"
                              value="Login"
                              title="Submit form"
                              className="icon-arrow-right">
                              <span>Login</span>
                         </button>
                    </form>
                    <Link to='/register' id="link-container">
                         <div id="link">Don't Have An Account? Register Here</div>
                    </Link>
               </section>
          </section>
     )
}

export default Login;