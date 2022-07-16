local key=KEYS[1]
local ttl=ARGV[1]
local maxCount=tonumber(ARGV[2])
if redis.call('EXISTS', key) == 0 then
	redis.call('SETEX', key, ttl, '1')
	return 1
else 
	local count = tonumber(redis.call('INCR', key))
	if count > maxCount then 
		return -1
	end
	return count
end
