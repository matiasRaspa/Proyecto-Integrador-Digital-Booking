import ReactDOM from "react-dom";

const ModalPortal = ( {children} ) => {
  

  return ReactDOM.createPortal(
    <>
     {children}
    </>,
    document.getElementById("portal")
  );
};

export default ModalPortal;