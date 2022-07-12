import React, { useContext, useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { Link, useNavigate } from 'react-router-dom';
import { UserContext } from '../../UserContext';
import { AlertModal } from './modals/AlertModal';
import { fechPost } from '../../helper/fetchPost';
import { SchemaLoginForm } from './ValidationSchemas';
import Alert from '../modals/alert/Alert';

export const LoginScreen = () => {

  const navigate = useNavigate();

  const [alert, setAlert] = useState("");
  const { setAuth } = useContext(UserContext);
  const [showPass, setShowPass] = useState(false);

  const toggleBtnPass = () => {
    setShowPass(prevState => !prevState)
  }

  return (

    <main className='login__container'>

      <Alert />

      <Formik
        initialValues={{
          email: '',
          password: ''
        }
        }

        validationSchema = {SchemaLoginForm}

        onSubmit={async (valores, { resetForm }) => {
          const url = "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/users/authenticate";
          
          let data = {
            username: valores.email,
            password: valores.password,
          };

          const { jwt, status } = await fechPost(url, data);

          if (status === 200) {
            resetForm();
            localStorage.setItem('token', `Bearer ${jwt}`);
            setAuth(true);
            setTimeout(() => navigate('/'), 500)
          }
          else {
            setAlert()
          }
        }}
      >
        {({ errors, touched }) => (
          <Form className='login__form' >

            <h2 className='title-form'>Iniciar sesión</h2>
        
            <div className='box-input'>
              <label htmlFor="email">Correo electrónico</label>
              <Field className={`field ${errors.email && touched.email ? "input-error" : ""}`}
                type="email"
                id="email"
                name='email'
              />
              <ErrorMessage name='email' component={() => (
                <div className='error'>{errors.email}</div>
              )} />
            </div>
            
            <div className='box-input'>
              <label htmlFor="password">Contraseña</label>

              <div className='field-pass'>
                <span className='btn-eye' onClick={toggleBtnPass}>
                  {
                    showPass ? <FontAwesomeIcon className="icon-eye" icon={faEye} /> : <FontAwesomeIcon className="icon-eye" icon={faEyeSlash} />
                  }
                </span>
                <Field className={`field ${errors.password && touched.password ? "input-error" : ""}`}
                  type={showPass ? "text" : "password"}
                  id="password"
                  name='password'
                  autoComplete="off"
                />
              </div>

              <ErrorMessage name='password' component={() => (
                <div className='error'>{errors.password}</div>
              )} />
            </div>
            
            <AlertModal alert={alert} />
            <button type='submit'>Ingresar</button>
            <p>¡Aún no tienes cuenta?

              <Link to={'/register'}>
                Registrate
              </Link>

            </p>

          </Form>
        )}
      </Formik>

    </main >

  );
}

