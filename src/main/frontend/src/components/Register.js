import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import PulseLoader from 'react-spinners/PulseLoader'
import axios from 'axios'
import '../styles/register.scss'

const Register = () => {
     const [username, setUsername] = useState("");
     const [password, setPassword] = useState("");
     const [verifyPassword, setVerifyPassword] = useState("");
     const [loading, setLoading] = useState(false);

     const handleSubmit = async (e) => {
          e.preventDefault();
          if (verifyPassword !== password) {
               alert("Passwords Don't Match!")
          } else {
               setLoading(true);
               const res = await axios.post('/user/create', { username, password })
               setLoading(false);
               console.log(res.data);
          }
     }

     return (
          <section id="register">
               <section id="register-container" className="fade-in">
                    <div id="register-title">Register</div>
                    <form id="register-form" onSubmit={handleSubmit}>
                         <input
                              className="register-input"
                              type="text"
                              required={true}
                              name="username"
                              placeholder="Username"
                              onChange={e => setUsername(e.target.value)}
                         />
                         <input
                              className="register-input"
                              type="password"
                              required={true}
                              name="password"
                              placeholder="Password"
                              onChange={e => setPassword(e.target.value)}
                         />
                         <input
                              className="register-input"
                              type="password"
                              required={true}
                              name="password"
                              placeholder="Verify Password"
                              onChange={e => setVerifyPassword(e.target.value)}
                         />

                         {loading &&
                              <section id="loading-container">
                                   <PulseLoader size={25} color={"#BBE1FA"} loading={loading} />
                              </section>
                         }

                         {!loading &&
                              <button
                                   id="register-button"
                                   type="submit"
                                   value="Submit"
                                   title="Submit form"
                                   className="icon-arrow-right">
                                   <span>Submit</span>
                              </button>
                         }
                    </form>
                    <Link to='/' id="link-container">
                         <div id="link">Already Have An Account? Login Here</div>
                    </Link>
               </section>
          </section>
     )
}

export default Register;