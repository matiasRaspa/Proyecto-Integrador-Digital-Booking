import React from "react";
import {
        FacebookShareButton,
        WhatsappShareButton,
        TwitterShareButton,
        LinkedinShareButton,
        FacebookIcon,
        WhatsappIcon,
        TwitterIcon,
        LinkedinIcon,

} from "react-share";

export default function SocialMedia({ shareUrl }) {
        return (
                <div>
                        <FacebookShareButton url={shareUrl} quote="Facebook">
                                <FacebookIcon size={32} round />
                        </FacebookShareButton>

                        <WhatsappShareButton url={shareUrl} quote="Whatsapp">
                                <WhatsappIcon size={32} round />
                        </WhatsappShareButton >

                        <TwitterShareButton url={shareUrl} quote="Twitter">
                                <TwitterIcon size={32} round/>
                        </TwitterShareButton>

                        <LinkedinShareButton url={shareUrl} quote="Linkedin">
                                <LinkedinIcon size={32} round/>
                        </LinkedinShareButton>
                </div>

        );
}


