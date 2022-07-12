import {
    Overlay,
    ModalContainer,
    TextContainer,
} from "./LikeModalStyled";

import Button from "./Button";

export default function LikeModal({ stateModal, setStateModal }) {

    return (
        <>
            {stateModal && (
                <Overlay>
                    <ModalContainer>
                        <TextContainer>
                            <h3>Iniciá sesión o registrate </h3>
                        </TextContainer>
                        <div className="buttons">
                            <Button
                                handlerEvent={() => setStateModal(false)}
                                text="Volver"
                            />
                            <Button
                                handlerEvent={() => setStateModal(false)}
                                text="Login"
                                to="/login"
                            />
                        </div>

                    </ModalContainer>
                </Overlay>
            )}
        </>
    );
};
