import React, { useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { Link } from 'react-router-dom';
import { AlertModal } from './modals/AlertModal';
import { SchemaRegisterForm } from './ValidationSchemas';
import { SuccessRegister } from '../modals/SuccessRegister';
import ModalPortal from '../modals/ModalPortal';

export const RegisterScreen = () => {

  const [success, setsuccess] = useState(false)
  const [alert, setAlert] = useState(false);
  const [alertType, setAlertType] = useState("");
  const [showPass, setShowPass] = useState(false);
  const [showConfirmPass, setShowConfirmPass] = useState(false);

  const toggleBtnPass = () => {
    setShowPass(prevState => !prevState)
  }
  const toggleBtnConfirmPass = () => {
    setShowConfirmPass(prevState => !prevState)
  }

  return (
    <>
      <ModalPortal >
        <SuccessRegister state={success} />
      </ModalPortal>
      <main className='register__container'>
        <Formik
          initialValues={{
            name: '',
            lastname: '',
            email: '',
            phone: '',
            password: '',
            confirmPassword: ''
          }
          }

          validationSchema={SchemaRegisterForm}

          onSubmit={async (valores, { resetForm }) => {

            const url = "http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/users/signup"
            let data = {
              name: valores.name,
              lastName: valores.lastname,
              email: valores.email,
              phone: valores.phone,
              password: valores.password
            };

            const resp = await fetch(url, {
              method: "POST",
              headers: {
                "Content-Type":
                  "application/json",
              },
              body: JSON.stringify(data)
            });

            const status = await resp.status;

            if (status === 201) {
              resetForm();
              setsuccess(true)
            }
            else {
              setAlertType("error");
              setAlert(true);
              setTimeout(() => (setAlert(false)), 3000);
            }
          }}
        >
          {({ errors, touched }) => (
            <Form className='register__form' >
              <h2 className='title-form'>Crear cuenta</h2>

              <div className='fullname'>
                <div className='fullname-info'>
                  <label htmlFor="name">Nombre</label>
                  <Field className={`field ${errors.name && touched.name ? "input-error" : ""}`}
                    type="text"
                    id="name"
                    name='name'
                  />
                  <ErrorMessage name='name' component={() =>
                    <div className='error'>{errors.name}</div>
                  } />
                </div>

                <div className='fullname-info'>
                  <label htmlFor="lastname">Apellido</label>
                  <Field className={`field ${errors.lastname && touched.lastname ? "input-error" : ""}`}
                    type="text"
                    id="lastname"
                    name='lastname'
                  />
                  <ErrorMessage name='lastname' component={() => (
                    <div className='error'>{errors.lastname}</div>
                  )} />
                </div>

              </div>

              <div className='fullname'>
                <div className='fullname-info'>
                  <label htmlFor="name">Correo electrónico</label>
                  <Field className={`field ${errors.email && touched.email ? "input-error" : ""}`}
                    type="email"
                    id="email"
                    name='email'
                  />
                  <ErrorMessage name='email' component={() =>
                    <div className='error'>{errors.email}</div>
                  } />
                </div>

                <div className='fullname-info'>
                  <label htmlFor="lastname">Teléfono</label>
                  <Field className={`field ${errors.phone && touched.phone ? "input-error" : ""}`}
                    type="text"
                    id="phone"
                    name='phone'
                  />
                  <ErrorMessage name='phone' component={() => (
                    <div className='error'>{errors.phone}</div>
                  )} />
                </div>

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

              <div className='box-input'>
                <label htmlFor="confirmPassword">Confirmar contraseña</label>
                <div className='field-pass'>
                  <span className='btn-eye' onClick={toggleBtnConfirmPass}>
                    {
                      showConfirmPass ? <FontAwesomeIcon className="icon-eye" icon={faEye} /> : <FontAwesomeIcon className="icon-eye" icon={faEyeSlash} />
                    }
                  </span>
                  <Field className={`field ${errors.confirmPassword && touched.confirmPassword ? "input-error" : ""}`}
                    type={showConfirmPass ? "text" : "password"}
                    id="confirmPassword"
                    name='confirmPassword'
                    autoComplete="off"
                  />
                </div>
                <ErrorMessage name='confirmPassword' component={() => (
                  <div className='error'>{errors.confirmPassword}</div>
                )} />
              </div>

              <AlertModal alert={alert} alertType={alertType} />
              <button type='submit' >Crear cuenta</button>
              <p>¿Ya tienes una cuenta?
                <Link to={'/login'}>
                  Iniciar sesión
                </Link>
              </p>
            </Form>
          )}
        </Formik>
      </main>
    </>
  );

}
