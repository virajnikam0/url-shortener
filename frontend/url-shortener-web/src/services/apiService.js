import axios from 'axios';
import {API_URL} from "../utils/appConstants"


export const getShortUrl = async (inputModel) =>{
    const request = await axios.post(
        `${API_URL}/api/UrlShortener/getShortUrl`,
        {...inputModel});
    return request;
}

export const getLongUrl = async (inputModel) =>{
    const request = await axios.post(
        `${API_URL}/api/UrlShortener/getLongUrl`,
        {...inputModel});
    return request;
}