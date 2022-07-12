import styled from 'styled-components'


export const Overlay = styled.div`
width: 100%;
height: 100%;
position: fixed;
top: 0;
left: 0;
background: rgba(0,0,0,.5);
display: flex;
align-items: center;
justify-content: center;
z-index: 100;
`

export const ModalContainer = styled.div`
display: flex;
margin: 5%;
flex-direction: column;
justify-content: space-evenly;
align-items: center;
width: 538px;
height: 200px;
min-height: 100px;
background: #F7F7F7;
position: relative;
border-radius: 5px;
box-shadow: rgba(100,100,111,0.5) 0px 7px 29px 0px;

.buttons{
  display:flex;
}
.logo{
    width:100px;
}
 
`

export const TextContainer = styled.div`
h3 {
  height: 1.75rem;
  color: #31363F;
  font-weight: 600;
  text-align: center;
}

p {
  font-weight: 300;
  font-size: 0.9rem;
  color: #545776; 
  font-weight: bold;
  padding-top:10px;
  text-align: center;
}

`