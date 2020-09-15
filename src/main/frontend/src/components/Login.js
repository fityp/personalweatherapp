import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import PulseLoader from 'react-spinners/PulseLoader'
import '../styles/login.scss'

const Login = () => {
     const [username, setUsername] = useState("");
     const [password, setPassword] = useState("");
     const [loading, setLoading] = useState(false);

     const handleSubmit = (e) => {
          e.preventDefault();
          setLoading(true);
          setTimeout(() => setLoading(false), 5000)
          console.log("Form submitted..")
          console.log(username)
          console.log(password)
     }

     return (
          <section id="login">
               <section id="login-container" className="fade-in">
                    <div id="login-title">Login</div>
                    <form id="login-form" onSubmit={handleSubmit}>
                         <input
                              className="login-input"
                              type="text"
                              required='true'
                              name="username"
                              placeholder="Username"
                              onChange={e => setUsername(e.target.value)}
                         />
                         <input
                              className="login-input"
                              type="password"
                              required='true'
                              name="password"
                              placeholder="Password"
                              onChange={e => setPassword(e.target.value)}
                         />

                         {loading &&
                              <section id="loading-container">
                                   <PulseLoader size={25} color={"#BBE1FA"} loading={loading} />
                              </section>
                         }

                         {!loading &&
                              <button
                                   id="login-button"
                                   type="submit"
                                   value="Login"
                                   title="Submit form"
                                   className="icon-arrow-right">
                                   <span>Login</span>
                              </button>
                         }
                    </form>
                    <Link to='/register' id="link-container">
                         <div id="link">Don't Have An Account? Register Here</div>
                    </Link>
               </section>
          </section>
     )
}

export default Login;