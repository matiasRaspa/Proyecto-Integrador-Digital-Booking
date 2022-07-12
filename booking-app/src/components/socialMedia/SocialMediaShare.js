import SocialMedia from "./SocialMedia";
import * as DropdownMenuPrimitive from '@radix-ui/react-dropdown-menu';
import SocialMediaIcon from "../../img/SocialMedia";
import { styled } from '@stitches/react';


const SocialMediaShare = () => {

    const url = window.location.href;
    const StyledContent = styled(DropdownMenuPrimitive.Content, {
        minWidth: 220,
    });

    const itemStyles = {
        all: 'unset',
        fontSize: 13,
        lineHeight: 1,
        borderRadius: 3,
        display: 'flex',
        alignItems: 'center',
        height: 50,
        padding: '0 5px',
        position: 'absolute',
        paddingLeft: 25,
        userSelect: 'none',
    };

    const StyledItem = styled(DropdownMenuPrimitive.Item, { ...itemStyles });
    const DropdownMenu = DropdownMenuPrimitive.Root;
    const DropdownMenuTrigger = DropdownMenuPrimitive.Trigger;
    const DropdownMenuContent = StyledContent;
    const DropdownMenuItem = StyledItem;

    const Box = styled('div', {
        paddingLeft: 30,
    });
    const IconButton = styled('button', {
        all: 'unset',
        fontFamily: 'inherit',
        borderRadius: '100%',
        height: 35,
        width: 35,
        display: 'inline-flex',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'white',

    });


    return (
        <Box>
            <DropdownMenu>
                <DropdownMenuTrigger asChild>
                    <IconButton aria-label="Customise options">
                        <SocialMediaIcon />
                    </IconButton>
                </DropdownMenuTrigger>
                <DropdownMenuContent sideOffset={4}>
                    <DropdownMenuItem>
                        <SocialMedia shareUrl={url} />
                    </DropdownMenuItem>
                </DropdownMenuContent>
            </DropdownMenu>
            
        </Box>

    );
};
export default SocialMediaShare;