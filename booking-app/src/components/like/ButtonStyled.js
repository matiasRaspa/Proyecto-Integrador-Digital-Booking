import styled from "styled-components";

export const ButtonWrap = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  opacity:${(props) => props.opacity === true && `0.4`};
  width: 206px;
  height: 40px;
  border: 1px solid F0572D;
  background-color: ${(props) =>
    props.theme === "primary" ? "#F7F7F7" : "#F0572D"};
  border-radius: 5px;
  margin-right: 5px;
  color: ${(props) =>
    props.theme === "primary" ? "#293242" : "#F7F7F7"};
  font-weight: 700;
  font-size: 1rem;
  text-align: center;
  :hover{
    background-color: ${(props) =>
      props.theme === "primary" ? "#293242" : "#F7F7F7"};
    color: ${(props) =>
      props.theme === "primary" ? "#F7F7F7" : "#293242"};
    border-width: 1px;
    border-style: solid;
    border-color:${(props) =>
      props.theme === "primary" ? "#F7F7F7" : "#F0572D"};
    border-color: ${(props) => props.disabled === true && "#B00020"};
    color: ${(props) => props.disabled === true && "#B00020"};

  };
 
  
`;
