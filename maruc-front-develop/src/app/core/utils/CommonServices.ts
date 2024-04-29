//create a function that converts time to a string with the format AAAA/MM/DD
export function convertTimeToString(time: number): string {
    const date = new Date(time);
    const year = date.getFullYear();
    //if month is less than 10, add a 0 before it
    const month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
    //if day is less than 10, add a 0 before it
    const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    return `${day}/${month}/${year}`;
    }

export function convertTimeToStringWithTime(time: number): string {
    const date = new Date(time);
    const year = date.getFullYear();
    //if month is less than 10, add a 0 before it
    const month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
    //if day is less than 10, add a 0 before it
    const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    //get the hours in 12 hour format
    const hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    const ampm = date.getHours() >= 12 ? 'PM' : 'AM';
    const ret = `${day}/${month}/${year} ${hours}:${minutes} ${ampm}`;
    return ret;
    }

