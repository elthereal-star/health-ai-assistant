import request from './request'

export const getHealthAdvice = (data: any) => request.post('/ai/health-advice', data)
